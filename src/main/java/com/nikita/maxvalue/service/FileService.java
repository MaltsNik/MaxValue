package com.nikita.maxvalue.service;

public interface FileService {
    /**
     * Находит N-е максимальное число в файле
     *
     * @param filePath путь к файлу
     * @param n позиция числа
     * @return N-е максимальное число
     * @throws IllegalArgumentException если n некорректно
     * @throws RuntimeException если возникла ошибка при чтении файла
     */
    Long findMaxNumber(String filePath, int n);
}
