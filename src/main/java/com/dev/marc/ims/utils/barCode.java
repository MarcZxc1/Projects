package com.dev.marc.ims.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class barCode {

	public static void generateQRCode() throws IOException, WriterException {
		String data = "https://app.gradient.network/dashboard";
		final String ASSETS_PATH = "/assets/";
		String fileName = "QRSamples.png";
		String charset = "UTF-8";

		Map<EncodeHintType, ErrorCorrectionLevel> hashMap = new HashMap<>();
		hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

		BitMatrix bitMatrix = new MultiFormatWriter().encode(
				new String( ASSETS_PATH .getBytes(charset), charset),
				BarcodeFormat.QR_CODE, 500, 500);

		Path path = FileSystems.getDefault().getPath(fileName);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		System.out.println("Generated QR code");
	}

}