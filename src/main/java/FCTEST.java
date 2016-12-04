import java.io.IOException;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

import pi.Gyro;

public class FCTEST {

	public static void main(String[] args) throws UnsupportedBusNumberException, IOException, InterruptedException {
		I2CBus i2c = I2CFactory.getInstance(I2CBus.BUS_1);
//		Gyro gyro=new Gyro(i2c);
		Accel gy80=new Accel(i2c);
		while (true) {
//			gyro.readGyro();
			gy80.readAxis();
			Thread.sleep(100);
		}

	}

}
