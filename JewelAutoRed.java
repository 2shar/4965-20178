package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by tushar on 10/2/17.
 */


@Autonomous(name = "JewelAutoRed", group = "Autonomous")

public class JewelAutoRed extends LinearOpMode {



    DcMotor leftMotor;
    DcMotor rightMotor;
    Servo jewelServo;

    ColorSensor jewelColor;
    float jewelColorHSV[] = {0, 0, 0};

    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        rightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        jewelServo = hardwareMap.servo.get("jewelServo");
        jewelColor = hardwareMap.colorSensor.get("jewelColor");

        jewelColor.enableLed(true);

        telemetry.addData("color R", jewelColor.red());
        telemetry.addData("color G", jewelColor.green());
        telemetry.addData("color B", jewelColor.blue());
        telemetry.update();
        waitForStart();


        jewelServo.setPosition(1);



        sleep(2000);
        Color.RGBToHSV(jewelColor.red(), jewelColor.green(), jewelColor.blue(), jewelColorHSV);

        telemetry.addData("color R", jewelColor.red());
        telemetry.addData("color G", jewelColor.green());
        telemetry.addData("color B", jewelColor.blue());
        telemetry.addData("jewelColorHSV", jewelColorHSV[0]);
        telemetry.update();

        if(jewelColorHSV[0] < 100 || jewelColorHSV[0] > 300 ){

            telemetry.addData("dogs", "eat dogs1");
            telemetry.update();
            leftMotor.setPower(-1);
            rightMotor.setPower(1);
        }
        if(jewelColorHSV[0] > 180 && jewelColorHSV[0] < 270 ){

            telemetry.addData("dogs", "eat dogs");
            telemetry.update();
           leftMotor.setPower(1);
           rightMotor.setPower(-1);
        }
        sleep(0300);

        leftMotor.setPower(0);
        rightMotor.setPower(0);

        jewelServo.setPosition(0);

        sleep(1000);


    }
}