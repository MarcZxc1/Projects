module com.dev.marc.ims {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.web;

	requires org.controlsfx.controls;
	requires com.dlsc.formsfx;
	requires net.synedra.validatorfx;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.bootstrapfx.core;
	requires eu.hansolo.tilesfx;
	requires com.almasb.fxgl.all;
	requires javafx.media;
	requires com.google.zxing;
	requires java.desktop;
	requires java.sql;

	opens com.dev.marc.ims to javafx.fxml;
	exports com.dev.marc.ims;
	exports com.dev.marc.ims.controller;
	opens com.dev.marc.ims.controller to javafx.fxml;
}