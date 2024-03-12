package com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.PatientResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.PatientNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.Serialization;
import com.github.stanislavbukaevsky.patientrecordsystem.service.Service;
import com.github.stanislavbukaevsky.patientrecordsystem.service.impl.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PatientSerialization implements Serialization<HttpServletRequest, HttpServletResponse, String> {
    private final static PatientSerialization INSTANCE = new PatientSerialization();
    private final Service<PatientRequestDto, PatientRequestUpdateDto, PatientResponseDto, Long> patientService = PatientService.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private PatientSerialization() {
    }

    public static PatientSerialization getInstance() {
        return INSTANCE;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, String requestHeader) throws IOException {
        String answer = "";
        PatientRequestDto patientRequest;

        try {
            patientRequest = objectMapper.readValue(requestHeader, PatientRequestDto.class);
            PatientResponseDto patientResponse = patientService.save(patientRequest);
            response.setStatus(HttpServletResponse.SC_OK);
            answer = objectMapper.writeValueAsString(patientResponse);
        } catch (PatientNotFoundException p) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            answer = p.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response, String requestHeader) throws IOException {
        String answer = "";
        PatientRequestUpdateDto patientRequest;

        try {
            patientRequest = objectMapper.readValue(requestHeader, PatientRequestUpdateDto.class);
            PatientResponseDto patientResponse = patientService.update(patientRequest);
            response.setStatus(HttpServletResponse.SC_OK);
            answer = objectMapper.writeValueAsString(patientResponse);
        } catch (PatientNotFoundException p) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            answer = p.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String answer = "";

        try {
            String uri = request.getRequestURI();
            if (uri.equals("/patients/all")) {
                List<PatientResponseDto> patientResponse = patientService.findAll();
                response.setStatus(HttpServletResponse.SC_OK);
                answer = objectMapper.writeValueAsString(patientResponse);
            } else {
                String[] findId = uri.split("/");
                Long id = Long.parseLong(findId[2]);
                PatientResponseDto patientResponse = patientService.findById(id);
                response.setStatus(HttpServletResponse.SC_OK);
                answer = objectMapper.writeValueAsString(patientResponse);
            }
        } catch (PatientNotFoundException p) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            answer = p.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String answer = "";

        try {
            String uri = request.getRequestURI();
            String[] findId = uri.split("/");
            Long id = Long.parseLong(findId[2]);
            String deletePatient = patientService.delete(id);
            response.setStatus(HttpServletResponse.SC_OK);
            answer = objectMapper.writeValueAsString(deletePatient);
        } catch (PatientNotFoundException p) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            answer = p.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }
}
