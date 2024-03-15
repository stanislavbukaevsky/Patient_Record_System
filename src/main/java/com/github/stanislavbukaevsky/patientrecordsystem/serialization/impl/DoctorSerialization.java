package com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DoctorNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.Serialization;
import com.github.stanislavbukaevsky.patientrecordsystem.service.Service;
import com.github.stanislavbukaevsky.patientrecordsystem.service.impl.DoctorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Класс для сериализации в JSON формат для врача.
 * Реализует интерфейс {@link Serialization}. Параметры: <br>
 * {@link HttpServletRequest} - запрос пользователя <br>
 * {@link HttpServletResponse} - ответ пользователю <br>
 * {@link String} - объект строки
 */
public class DoctorSerialization implements Serialization<HttpServletRequest, HttpServletResponse, String> {
    private final static DoctorSerialization INSTANCE = new DoctorSerialization();
    private final Service<DoctorRequestDto, DoctorRequestUpdateDto, DoctorResponseDto, Long> doctorService = DoctorService.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private DoctorSerialization() {
    }

    public static DoctorSerialization getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод преобразует в JSON для сохранения врача
     *
     * @param request       запрос пользователя
     * @param response      ответ пользователю
     * @param requestHeader запрос пользователя, преобразованный в строку
     * @throws IOException исключение ввода/вывода
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, String requestHeader) throws IOException {
        String answer = "";
        DoctorRequestDto doctorRequest;

        try {
            doctorRequest = objectMapper.readValue(requestHeader, DoctorRequestDto.class);
            DoctorResponseDto doctorResponse = doctorService.save(doctorRequest);
            response.setStatus(HttpServletResponse.SC_OK);
            answer = objectMapper.writeValueAsString(doctorResponse);
        } catch (DoctorNotFoundException d) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            answer = d.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }

    /**
     * Этот метод преобразует в JSON для изменения врача
     *
     * @param request       запрос пользователя
     * @param response      ответ пользователю
     * @param requestHeader запрос пользователя, преобразованный в строку
     * @throws IOException исключение ввода/вывода
     */
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response, String requestHeader) throws IOException {
        String answer = "";
        DoctorRequestUpdateDto doctorRequest;

        try {
            doctorRequest = objectMapper.readValue(requestHeader, DoctorRequestUpdateDto.class);
            DoctorResponseDto doctorResponse = doctorService.update(doctorRequest);
            response.setStatus(HttpServletResponse.SC_OK);
            answer = objectMapper.writeValueAsString(doctorResponse);
        } catch (DoctorNotFoundException d) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            answer = d.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }

    /**
     * Этот метод преобразует в JSON для получения врача
     *
     * @param request  запрос пользователя
     * @param response ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String answer = "";

        try {
            String uri = request.getRequestURI();
            if (uri.equals("/doctors/all")) {
                List<DoctorResponseDto> doctorResponse = doctorService.findAll();
                response.setStatus(HttpServletResponse.SC_OK);
                answer = objectMapper.writeValueAsString(doctorResponse);
            } else {
                String[] findId = uri.split("/");
                Long id = Long.parseLong(findId[2]);
                DoctorResponseDto doctorResponse = doctorService.findById(id);
                response.setStatus(HttpServletResponse.SC_OK);
                answer = objectMapper.writeValueAsString(doctorResponse);
            }
        } catch (DoctorNotFoundException d) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            answer = d.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }

    /**
     * Этот метод преобразует в JSON для удаления врача
     *
     * @param request  запрос пользователя
     * @param response ответ пользователю
     * @throws IOException исключение ввода/вывода
     */
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String answer = "";

        try {
            String uri = request.getRequestURI();
            String[] findId = uri.split("/");
            Long id = Long.parseLong(findId[2]);
            String deleteDoctor = doctorService.delete(id);
            response.setStatus(HttpServletResponse.SC_OK);
            answer = objectMapper.writeValueAsString(deleteDoctor);
        } catch (DoctorNotFoundException d) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            answer = d.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }
}
