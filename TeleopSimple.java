package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by Tushar & Ronil & Hamza & Will & Machine on 10/22/16.
 */
@TeleOp(name="SimpleTeleOp", group="Test")
public class TeleopSimple extends OpMode
{

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor horizontal;
    DcMotor shoulder;
    DcMotor elbow;

    Servo wrist;

    Servo clawLeft;
    Servo clawRight;

    long lastTime = 0;

    float targetPos = 0;
    int shoulderpos;

    Servo jewelServo;

    ColorSensor jewelColor;
    float jewelColorHSV[] = {0, 0, 0};

    public void init()
    {

        leftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        rightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        horizontal = hardwareMap.dcMotor.get("hor");
        shoulder = hardwareMap.dcMotor.get("shoulder");
        elbow = hardwareMap.dcMotor.get("elbow");

        wrist = hardwareMap.servo.get("wrist");
        clawLeft = hardwareMap.servo.get("clawLeft");
        clawRight = hardwareMap.servo.get("clawRight");

        lastTime = System.currentTimeMillis();

       /* toeVertical = hardwareMap.servo.get("toeVertical");
        toeHorizontal = hardwareMap.servo.get("toeHorizontal");
        clawLeft = hardwareMap.servo.get("clawLeft");
        clawRight = hardwareMap.servo.get("clawRight");
        pusher = hardwareMap.servo.get("pusher");*/

        telemetry.addData("HTPA" , horizontal.getTargetPosition());



        horizontal.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        shoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        shoulder.setTargetPosition(0);
        shoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        elbow.setTargetPosition(0);
        elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);



        jewelServo = hardwareMap.servo.get("jewelServo");
        jewelColor = hardwareMap.colorSensor.get("jewelColor");

        jewelColor.enableLed(true);


        telemetry.addData("HTPB" , horizontal.getTargetPosition());
        telemetry.update();

    }

    public void loop()
    {

        long dt = System.currentTimeMillis()-lastTime;
        lastTime += dt;


        //Drive motors
        leftMotor.setPower(gamepad1.left_stick_y / (gamepad1.right_trigger * 4 + 1));
        rightMotor.setPower(gamepad1.right_stick_y / (gamepad1.right_trigger * 4 + 1));



       /* if(gamepad2.y){
            horizontal.setPower(0.4 / (gamepad2.right_trigger * 2 + 1));
        }
        else if(gamepad2.a){
            horizontal.setPower(-0.4 / (gamepad2.right_trigger * 2 + 1));
        }
        else{
            horizontal.setPower(0);
        }*/
        //shoulder.setPower(gamepad2.left_stick_y * 0.5);
        //shoulder.setPower(gamepad2.left_stick_y * 0.5 + Math.abs(gamepad2.left_stick_y) * (2 * (float) (shoulder.getCurrentPosition())) / -720);

        /*//if(gamepad2.left_stick_y!= 0) {
            if (shoulder.getCurrentPosition() > 0) {
                shoulder.setPower(0.5 * (0.5 * gamepad2.left_stick_y + Math.abs(gamepad2.left_stick_y) * 0.02 * Math.sqrt(shoulder.getCurrentPosition())));
            } else {
                shoulder.setPower(0.5 * (0.5 * gamepad2.left_stick_y - Math.abs(gamepad2.left_stick_y) * 0.02 * Math.sqrt(-shoulder.getCurrentPosition())));
            }
       // }*/
        /*
        else{
            if(oldLeftStickY != 0){
                shoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                shoulder.setTargetPosition(shoulder.getCurrentPosition());
                leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                leftMotor.setPower(0.1);
            }
            else {
                leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                leftMotor.setPower(0.1);
            }
            /*if(shoulder.getCurrentPosition() > oldShoulderPosition){
                shoulder.setPower(-0.1);
            }
            else if(shoulder.getCurrentPosition() < oldShoulderPosition){
                shoulder.setPower(0.1);
            }
            else{
                shoulder.setPower(0);
            }

        }*/




        //elbow using shouderpos                                 DAVIDS DETATIME METHOD THAT HAS A GLITCH

       /* shoulderpos = elbow.getCurrentPosition();

        targetPos += gamepad2.right_stick_y;
        if (lasttime != 0) {
            double dt = time-lasttime;
            elbow.setTargetPosition((int) (dt*gamepad2.right_stick_y * 30 + shoulderpos));
            lasttime += dt;
        }
        elbow.setPower(1);*/


      //  horizontal.setTargetPosition((int)Math.round((dt * gamepad2.left_stick_x * .01 + horizontal.getTargetPosition())));
        horizontal.setPower(gamepad2.left_stick_x * 0.4);


        shoulder.setTargetPosition((int)Math.floor((dt * gamepad2.left_stick_y * -0.5 + shoulder.getTargetPosition())));
        shoulder.setPower(1);

        elbow.setTargetPosition((int)Math.floor((dt * gamepad2.right_stick_y * .2 + elbow.getTargetPosition())));
        elbow.setPower(1);




        if(gamepad2.dpad_left){
            wrist.setPosition(0.2);
        }
        else if(gamepad2.dpad_right){
            wrist.setPosition(0.8);
        }
        else {
            wrist.setPosition(0.2 * gamepad2.right_stick_x + 0.5);
        }


        clawLeft.setPosition(gamepad2.right_trigger + 0.5);
        clawRight.setPosition(0.5 - gamepad2.right_trigger);



        if(gamepad2.left_bumper){
            jewelServo.setPosition(0);
        }
        else if(gamepad2.right_bumper){
            jewelServo.setPosition(1);
        }
        else{
            jewelServo.setPosition(0.5);
        }












        Color.RGBToHSV(jewelColor.red(), jewelColor.green(), jewelColor.blue(), jewelColorHSV);

        //Telemetry




       /* telemetry.addData("ColorBeaconHue", jewelColorHSV[0]);
        telemetry.addData("ColorBeaconSaturation", jewelColorHSV[1]);
        telemetry.addData("ColorBeaconValue", jewelColorHSV[2]);
        telemetry.addData("ColorBeaconR", jewelColor.red());
        telemetry.addData("ColorBeaconG", jewelColor.green());
        telemetry.addData("ColorBeaconB", jewelColor.blue());*/

        telemetry.addData("Time (s)" , lastTime/1000);
        telemetry.addData("dt (ms)" , dt);

        telemetry.addData("Hor" , horizontal.getCurrentPosition());
        telemetry.addData("Hor target" , horizontal.getTargetPosition());
        telemetry.addData("Shl" , shoulder.getCurrentPosition());
        telemetry.addData("Shl target" , shoulder.getTargetPosition());
        telemetry.addData("Elb" , elbow.getCurrentPosition());
        telemetry.addData("Elb target" , elbow.getTargetPosition());

       // telemetry.addData("Shoulder POwer - gamepad" , (2 * (float) (shoulder.getCurrentPosition())) / -720);
        //telemetry.addData("Shoulder Power" , shoulder.getPower() );
        //telemetry.addData("shoulder var" , shoulderPosition);
       // telemetry.addData("Hand Speed", handPower);
        telemetry.update();








    }

}