package hellofx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.naming.directory.InvalidAttributesException;

import org.apache.commons.io.IOUtils;
import org.curiousworks.BlueMarble;
import org.json.JSONObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlueMarbleController {

	@FXML
	private ImageView image;

	@FXML
	private DatePicker date;

	@FXML
	private CheckBox Enhaced;

	@FXML
	private CheckBox BlackOrWhite;

	@FXML
	void updateDate(ActionEvent event) {
		LocalDate Date = LocalDate.now();
		LocalDate imageD = LocalDate.of(date.getValue().getYear(), date.getValue().getMonthValue(), date.getValue().getDayOfMonth());
		try {
			if(imageD.isAfter(Date)) throw  new InvalidAttributesException();
			BlueMarble blueMarble = new BlueMarble();
			if((date.getValue().getYear() > 2018  || (date.getValue().getYear() == 2018 && date.getValue().getMonthValue() > 6)) && Enhaced.isSelected()) {
				Enhaced.setSelected(false);
			}
			blueMarble.setDate(date.getValue().getYear() + "-0" + date.getValue().getMonthValue() + "-" + date.getValue().getDayOfMonth());
			blueMarble.setEnhanced(Enhaced.isSelected());
			image.setImage(new Image(blueMarble.getImage()));
			
			if(BlackOrWhite.isSelected()) {
				ColorAdjust color = new ColorAdjust();
				color.setSaturation(-1);
				color.setContrast(1);
				image.setEffect(color);
			}else {
				ColorAdjust color = new ColorAdjust();
				color.setSaturation(0);
				color.setContrast(0);
				image.setEffect(color);
			}
		}catch(InvalidAttributesException error) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Wrong Date!");
			alert.setContentText("You went too far ahead!");
			alert.showAndWait();
		}catch(Exception error2) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR");
			alert.setContentText("Invalid date!");
			alert.showAndWait();
		}
		
	}}