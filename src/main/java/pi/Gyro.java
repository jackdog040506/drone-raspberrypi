package pi;

import java.io.IOException;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;

public class Gyro {
	private I2CDevice device;
	
	private static final byte ADRESS = 0x69;

	// Enable X, Y, Z-Axis and disable Power down mode
	private static final byte XYZ_POWER_CTL = 0x20;

	private static final byte xLSB = 0x28;
	private static final byte xMSB = 0x29;
	private static final byte yLSB = 0x2A;
	private static final byte yMSB = 0x2B;
	private static final byte zLSB = 0x2C;
	private static final byte zMSB = 0x2D;
	private static int xG;
	private static int yG;
	private static int zG;
	public Gyro(I2CBus bus) throws IOException {
		System.out.println("Starting Accel");
		device = bus.getDevice(ADRESS);
		device.write(XYZ_POWER_CTL, (byte) 0x0F);
		// Full scale range, 2000 dps default 250
		device.write(0x23, (byte)0x30);
		xG=0;
		yG=0;
		zG=0;
	}

	public void readGyro() throws IOException {
		// device.write(ADXL345_REG_DATAY0); //send data to register to read
		// Read 6 bytes of data from address 0x28(40)
		// X lsb, X msb, Y lsb, Y msb, Z lsb, Z msb
		byte[] data = new byte[6];
		data[0] = (byte) device.read(xLSB);
		data[1] = (byte) device.read(xMSB);
		data[2] = (byte) device.read(yLSB);
		data[3] = (byte) device.read(yMSB);
		data[4] = (byte) device.read(zLSB);
		data[5] = (byte) device.read(zMSB);

		int xGyro = ((data[1] & 0xFF) * 256) + (data[0] & 0xFF);
		if (xGyro > 32767)
		{
			xGyro -= 65536;
		}
		xG+=(xGyro>20||xGyro<-20)?xGyro:0;
		int yGyro = ((data[3] & 0xFF) * 256) + (data[2] & 0xFF);
		if (yGyro > 32767)
		{
			yGyro -= 65536;
		}
		yG+=(yGyro>20||yGyro<-20)?yGyro:0;
		int zGyro = ((data[5] & 0xFF) * 256) + (data[4] & 0xFF);
		if (zGyro > 32767)
		{
			zGyro -= 65536;
		}
		zG+=(zGyro>20||zGyro<-20)?zGyro:0;
		// Output data to screen
		
		System.out.println("X-Axis :"+ xG+"\t Y-Axis :"+ yG+"\t Z-Axis :"+ zG);
	}

	public int asInt(byte b) {
		int i = b;
		if (i < 0) {
			i = i + 256;
		}
		return i;
	}
}
