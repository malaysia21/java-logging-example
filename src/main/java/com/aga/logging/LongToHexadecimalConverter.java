package com.aga.logging;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.util.Random;

import static brave.internal.codec.HexCodec.toLowerHex;

@Log4j2
public class LongToHexadecimalConverter {

    public static void main(String[] args) {

        //Method1
        long startTime1 = Instant.now().toEpochMilli();
        for (int i = 0; i <= 100; i++) {
            generateAndConvertLongWithStringFormatConversionToHexadecimal();
        }
        long endTime1 = Instant.now().toEpochMilli();

        //Method2
        long startTime2 = Instant.now().toEpochMilli();
        for (int i = 0; i <= 1000000; i++) {
            generateAndConvertLongUsingBitMaskConversionToHexadecimal();
        }
        long endTime2 = Instant.now().toEpochMilli();

        //Method3
        long startTime3 = Instant.now().toEpochMilli();
        for (int i = 0; i <= 1000000; i++) {
            generateAndConvertLongWithToHexJavaFunction();
        }
        long endTime3 = Instant.now().toEpochMilli();

        //Method4
        long startTime4 = Instant.now().toEpochMilli();
        for (int i = 0; i <= 1000000; i++) {
            generateAndConvertLongWithBraveLibrary();
        }
        long endTime4 = Instant.now().toEpochMilli();

        log.debug("Method1 - with String format() method:          " + (endTime1 - startTime1) + " milliseconds");
        log.debug("Method2 - with bytes (custom):                  " + (endTime2 - startTime2) + " milliseconds");
        log.debug("Method3 - with toHexString() method:            " + (endTime3 - startTime3) + " milliseconds");
        log.debug("Method4 - with brave.internal.codec.HexCodec:   " + (endTime4 - startTime4) + " milliseconds");

        convertLongWithAllMethods(15L);
        convertLongWithAllMethods(new Random().nextLong());
    }

    private static void generateAndConvertLongWithStringFormatConversionToHexadecimal() {
        long randomLong = new Random().nextLong();
        String.format("%016x", randomLong);
    }

    private static void generateAndConvertLongUsingBitMaskConversionToHexadecimal() {
        long randomLong = new Random().nextLong();
        char[] data = new char[16];
        writeHexLong(data, randomLong);
        new String(data, 0, 16);
    }

    private static void generateAndConvertLongWithToHexJavaFunction() {
        long randomLong = new Random().nextLong();
        String hexadecimalLong = Long.toHexString(randomLong);
        StringUtils.leftPad(hexadecimalLong, 16, '0');
    }

    private static void generateAndConvertLongWithBraveLibrary() {
        long randomLong = new Random().nextLong();
        toLowerHex(randomLong);
    }

    private static void writeHexLong(char[] data, long v) {
        writeHexByte(data, 0, (byte) ((v >>> 56L) & 0xff));
        writeHexByte(data, 2, (byte) ((v >>> 48L) & 0xff));
        writeHexByte(data, 4, (byte) ((v >>> 40L) & 0xff));
        writeHexByte(data, 6, (byte) ((v >>> 32L) & 0xff));
        writeHexByte(data, 8, (byte) ((v >>> 24L) & 0xff));
        writeHexByte(data, 10, (byte) ((v >>> 16L) & 0xff));
        writeHexByte(data, 12, (byte) ((v >>> 8L) & 0xff));
        writeHexByte(data, 14, (byte) (v & 0xff));
    }

    private static final char[] HEX_DIGITS =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static void writeHexByte(char[] data, int pos, byte b) {
        data[pos] = HEX_DIGITS[(b >> 4) & 0xf];
        data[pos + 1] = HEX_DIGITS[b & 0xf];
    }

    private static void convertLongWithAllMethods(long randomLong) {
        //Method1
        log.debug(String.format("%016x", randomLong));

        //Method2
        char[] myData = new char[16];
        writeHexLong(myData, randomLong);
        log.debug(new String(myData, 0, 16));

        //Method3
        String hexadecimalLong = Long.toHexString(randomLong);
        log.debug(StringUtils.leftPad(hexadecimalLong, 16, '0'));

        //Method4
        log.debug(toLowerHex(randomLong));

    }
}
