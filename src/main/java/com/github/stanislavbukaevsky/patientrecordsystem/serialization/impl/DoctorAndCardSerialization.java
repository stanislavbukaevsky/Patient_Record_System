package com.github.stanislavbukaevsky.patientrecordsystem.serialization.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardRequestDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardRequestUpdateDto;
import com.github.stanislavbukaevsky.patientrecordsystem.dto.DoctorAndCardResponseDto;
import com.github.stanislavbukaevsky.patientrecordsystem.exception.DoctorAndCardNotFoundException;
import com.github.stanislavbukaevsky.patientrecordsystem.serialization.Serialization;
import com.github.stanislavbukaevsky.patientrecordsystem.service.Service;
import com.github.stanislavbukaevsky.patientrecordsystem.service.impl.DoctorAndCardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Класс для сериализации в JSON формат для врача и карты пациента.
 * Реализует интерфейс {@link Serialization}. Параметры: <br>
 * {@link HttpServletRequest} - запрос пользователя <br>
 * {@link HttpServletResponse} - ответ пользователю <br>
 * {@link String} - объект строки
 */
public class DoctorAndCardSerialization implements Serialization<HttpServletRequest, HttpServletResponse, String> {
    private final static DoctorAndCardSerialization INSTANCE = new DoctorAndCardSerialization();
    private final Service<DoctorAndCardRequestDto, DoctorAndCardRequestUpdateDto, DoctorAndCardResponseDto, Long> doctorAndCardService = DoctorAndCardService.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private DoctorAndCardSerialization() {
    }

    public static DoctorAndCardSerialization getInstance() {
        return INSTANCE;
    }

    /**
     * Этот метод преобразует в JSON для сохранения врача и карты пациента
     *
     * @param request       запрос пользователя
     * @param response      ответ пользователю
     * @param requestHeader запрос пользователя, преобразованный в строку
     * @throws IOException исключение ввода/вывода
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response, String requestHeader) throws IOException {
        String answer = "";
        DoctorAndCardRequestDto doctorAndCardRequest;

        try {
            doctorAndCardRequest = objectMapper.readValue(requestHeader, DoctorAndCardRequestDto.class);
            DoctorAndCardResponseDto doctorAndCardResponse = doctorAndCardService.save(doctorAndCardRequest);
            response.setStatus(HttpServletResponse.SC_OK);
            answer = objectMapper.writeValueAsString(doctorAndCardResponse);
        } catch (DoctorAndCardNotFoundException dac) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            answer = dac.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }

    /**
     * Этот метод преобразует в JSON для изменения врача и карты пациента
     *
     * @param request       запрос пользователя
     * @param response      ответ пользователю
     * @param requestHeader запрос пользователя, преобразованный в строку
     * @throws IOException исключение ввода/вывода
     */
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response, String requestHeader) throws IOException {
        String answer = "";
        DoctorAndCardRequestUpdateDto doctorAndCardRequest;

        try {
            doctorAndCardRequest = objectMapper.readValue(requestHeader, DoctorAndCardRequestUpdateDto.class);
            DoctorAndCardResponseDto doctorAndCardResponse = doctorAndCardService.update(doctorAndCardRequest);
            response.setStatus(HttpServletResponse.SC_OK);
            answer = objectMapper.writeValueAsString(doctorAndCardResponse);
        } catch (DoctorAndCardNotFoundException dac) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            answer = dac.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }

    /**
     * Этот метод преобразует в JSON для получения врача и карты пациента
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
            if (uri.equals("/doctors-and-cards/all")) {
                List<DoctorAndCardResponseDto> doctorAndCardResponse = doctorAndCardService.findAll();
                response.setStatus(HttpServletResponse.SC_OK);
                answer = objectMapper.writeValueAsString(doctorAndCardResponse);
            } else {
                String[] findId = uri.split("/");
                Long id = Long.parseLong(findId[2]);
                DoctorAndCardResponseDto doctorAndCardResponse = doctorAndCardService.findById(id);
                response.setStatus(HttpServletResponse.SC_OK);
                answer = objectMapper.writeValueAsString(doctorAndCardResponse);
            }
        } catch (DoctorAndCardNotFoundException dac) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            answer = dac.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }

    /**
     * Этот метод преобразует в JSON для удаления врача и карты пациента
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
            String deleteDoctorAndCard = doctorAndCardService.delete(id);
            response.setStatus(HttpServletResponse.SC_OK);
            answer = objectMapper.writeValueAsString(deleteDoctorAndCard);
        } catch (DoctorAndCardNotFoundException dac) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            answer = dac.getMessage();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            answer = e.getMessage();
        }

        PrintWriter printWriter = response.getWriter();
        printWriter.write(answer);
        printWriter.flush();
    }
}
