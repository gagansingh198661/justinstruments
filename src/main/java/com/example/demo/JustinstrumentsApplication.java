package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JustinstrumentsApplication extends Application {

	private ConfigurableApplicationContext springContext;
	private Parent rootNode;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() throws Exception {
		springContext = SpringApplication.run(JustinstrumentsApplication.class);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/sample.fxml"));
		fxmlLoader.setControllerFactory(springContext::getBean);
		rootNode = fxmlLoader.load();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Just Instruments");
		stage.setScene(new Scene(rootNode, 700, 700));
		stage.show();
	}

	@Override
	public void stop() throws Exception {
		springContext.close();
	}
}
