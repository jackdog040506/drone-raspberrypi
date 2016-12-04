import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinMode;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.platform.PlatformAlreadyAssignedException;

public class ElectricityControl{
	public static void main(String[] args) throws InterruptedException, PlatformAlreadyAssignedException {
	
		final GpioController gpio = GpioFactory.getInstance();
		// provision gpio pin #01 as an output pin and turn on
		final GpioPinDigitalOutput pin1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "pin1", PinState.HIGH);
		final GpioPinDigitalOutput pin2 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "pin2", PinState.HIGH);
		final GpioPinDigitalOutput pin3 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "pin3", PinState.HIGH);
		final GpioPinDigitalOutput pin4 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_14, "pin4", PinState.HIGH);
		final GpioPinDigitalOutput pin5 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, "pin5", PinState.HIGH);
		final GpioPinDigitalOutput pin6 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18, "pin6", PinState.HIGH);
		final GpioPinDigitalOutput[] array={pin1,pin2,pin3,pin4,pin5,pin6};
		
        Scanner s=new Scanner(System.in);
        String input="";
		while (true) {
			System.out.println("wait for type：");
			input=s.nextLine();
			String[] data=input.split(",");
			if (data[1].equals("on")) {
				array[Integer.valueOf(data[0])].low();
				System.out.println(array[Integer.valueOf(data[0])].getName()+" is set to on");
			}else{
				array[Integer.valueOf(data[0])].high();
				System.out.println(array[Integer.valueOf(data[0])].getName()+" is set to off");
			}
		}
	
	
	}

}
