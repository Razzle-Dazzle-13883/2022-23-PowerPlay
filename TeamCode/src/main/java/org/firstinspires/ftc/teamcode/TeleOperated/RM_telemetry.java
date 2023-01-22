package org.firstinspires.ftc.teamcode.TeleOperated;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Arm_telemetry")
public class RM_telemetry extends OpMode {

    //motors
    DcMotor leftFront = null;
    DcMotor rightFront = null;
    DcMotor leftBack = null;
    DcMotor rightBack = null;
    Servo leftClaw = null;
    Servo rightClaw = null;
    DcMotor linearSlide = null;


    boolean armHold = false;



    public boolean turboMode = false;

    @Override
    public void init() {

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        linearSlide = hardwareMap.get(DcMotor.class, "linearSlides");


        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        leftFront.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
        leftBack.setPower(0);
        linearSlide.setPower(0);


        leftClaw.setPosition(0.5);
        rightClaw.setPosition(0.85);


        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);


    }

    @Override
    public void loop() {







        if (gamepad2.left_bumper == true) {
            leftClaw.setPosition(0.3);
             rightClaw.setPosition(0.85);


        }


        if (gamepad2.right_bumper == true) {
            leftClaw.setPosition(0.3 + .55);
            rightClaw.setPosition(0.85 - .55);

        }


        if (gamepad2.left_stick_y >= 0.3 || gamepad2.left_stick_y <= -0.3) {
            linearSlide.setPower(gamepad2.left_stick_y);
        }
        else {
            if (armHold == false) {
                linearSlide.setPower(0);
            }
        }
        telemetry.update();
        telemetry.addData("Arm Encoder Value",linearSlide.getCurrentPosition());
        if (gamepad2.dpad_up) {
            armHold = true;
            int armPos = linearSlide.getCurrentPosition();
            linearSlide.setTargetPosition(armPos);
            linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            linearSlide.setPower(.3);
        }
        if (gamepad2.dpad_down) {
            armHold = false;
            linearSlide.setPower(0);
        }



    }
}