package org.firstinspires.ftc.teamcode.TeleOperated;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Teleop")
public class test_teleop extends OpMode {

    //motors
    DcMotor leftFront = null;
    DcMotor rightFront = null;
    DcMotor leftBack = null;
    DcMotor rightBack = null;
    Servo leftClaw = null;
    Servo rightClaw = null;
    DcMotor linearSlideLeft = null;
    DcMotor linearSlideRight = null;

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
        linearSlideLeft = hardwareMap.get(DcMotor.class, "linearSlideLeft");
        linearSlideRight = hardwareMap.get(DcMotor.class, "linearSlideRight");


        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        leftFront.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
        leftBack.setPower(0);
        linearSlideLeft.setPower(0);
        linearSlideRight.setPower(0);


        leftClaw.setPosition(0.5);
        rightClaw.setPosition(0.85);


        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlideRight.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public void loop() {




        //strafing stuff + Turbomode stuff
        double x = gamepad1.left_stick_y;
        double y = -gamepad1.left_stick_x;
        double r = -gamepad1.right_stick_x;
        if (turboMode) {
            leftFront.setPower((x + y + r)/ 3);
            leftBack.setPower((x - y + r) / 3 );
            rightFront.setPower((x - y - r) / 3);
            rightBack.setPower((x + y - r) / 3);
        } else {
            leftFront.setPower((x + y + r) / 5);
            leftBack.setPower((x - y + r) / 5);
            rightFront.setPower((x - y - r) / 5);
            rightBack.setPower((x + y - r) / 5);
        }

        //Fastmode and slowmode
        if (gamepad1.y == true) {
            turboMode = true;
        } else if (gamepad1.a == true) {
            turboMode = false;
        }


        if (gamepad2.left_bumper == true) {
            leftClaw.setPosition(0.3);
             rightClaw.setPosition(0.85);


        }


        if (gamepad2.right_bumper == true) {
            leftClaw.setPosition(0.3 + .55);
            rightClaw.setPosition(0.85 - .55);

        }


        if (gamepad2.left_stick_y >= 0.3 || gamepad2.left_stick_y <= -0.3) {
            linearSlideLeft.setPower(gamepad2.left_stick_y);
            linearSlideRight.setPower(gamepad2.left_stick_y);
        }
        else {
            if (armHold == false) {
                linearSlideLeft.setPower(0);
                linearSlideRight.setPower(0);

                int armPos = linearSlideLeft.getCurrentPosition();
                linearSlideLeft.setTargetPosition(armPos);
                linearSlideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                linearSlideLeft.setPower(.3);

                int armPos2 = linearSlideRight.getCurrentPosition();
                linearSlideRight.setTargetPosition(armPos2);
                linearSlideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                linearSlideRight.setPower(.3);

            }


        }

    }
}