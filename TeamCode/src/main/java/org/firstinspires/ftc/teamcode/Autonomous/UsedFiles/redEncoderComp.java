package org.firstinspires.ftc.teamcode.Autonomous.UsedFiles;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "use this for red 1/21/2023")

public class redEncoderComp extends LinearOpMode {
    private DcMotor rightFront = null;
    private DcMotor rightBack;
    private DcMotor leftFront;
    private DcMotor leftBack;
    Servo leftClaw = null;
    Servo rightClaw = null;
    DcMotor linearSlide = null;


    int leftFrontPos = 0;
    int rightFrontPos = 0;
    int leftBackPos = 0;
    int rightBackPos = 0;
    int linearSlidePos = 0;




    @Override
    public void runOpMode() throws InterruptedException {
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        leftClaw = hardwareMap.get(Servo.class, "leftClaw");
        rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        linearSlide = hardwareMap.dcMotor.get("linearSlides");



        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftClaw.setPosition(1);
        rightClaw.setPosition(0);


        waitForStart();





        up(70*10, 1);

        forward(-400,.3);
        forward(-400,.2);
        forward(-400,.1);


        sleep(2000);

        up(2500, .3); //raise cone

        right(-800, 0.2);
        sleep(1500);

        leftClaw.setPosition(0.5);// open claw
        rightClaw.setPosition(0.85);
        sleep(1500);



        right(-850, 0.2);

        leftClaw.setPosition(1); // close claw
        rightClaw.setPosition(0);
        up(-1500, .25); //




    }



    private void up (int up, double speed) {
        linearSlidePos += up;

        linearSlide.setTargetPosition(linearSlidePos);

        linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        linearSlide.setPower(speed);

        while (opModeIsActive() && linearSlide.isBusy()) {
            idle();
        }
    }
    private void forward(int distance, double speed  ) {
        leftFrontPos = leftFrontPos + distance;
        rightFrontPos = rightFrontPos + distance;
        leftBackPos = leftBackPos + distance;
        rightBackPos = rightBackPos + distance;


        leftFront.setTargetPosition(leftFrontPos);
        rightFront.setTargetPosition(rightFrontPos);
        leftBack.setTargetPosition(leftBackPos);
        rightBack.setTargetPosition(rightBackPos);


        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setPower(speed);
        rightFront.setPower(speed);
        leftBack.setPower(speed);
        rightBack.setPower(speed);


        while (opModeIsActive() && leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy() && rightBack.isBusy()) {
            idle();
        }



    }


    private void right(int distance, double speed  ) {
        leftFrontPos = leftFrontPos + distance;
        rightFrontPos = rightFrontPos - distance;
        leftBackPos = leftBackPos - distance;
        rightBackPos = rightBackPos + distance;


        leftFront.setTargetPosition(leftFrontPos);
        rightFront.setTargetPosition(rightFrontPos);
        leftBack.setTargetPosition(leftBackPos);
        rightBack.setTargetPosition(rightBackPos);


        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setPower(speed);
        rightFront.setPower(speed);
        leftBack.setPower(speed);
        rightBack.setPower(speed);

        while (opModeIsActive() && leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy() && rightBack.isBusy()) {
            idle();
        }
    }


    private void left(int distance, double speed  ) {
        leftFrontPos = leftFrontPos + distance;
        rightFrontPos = rightFrontPos - distance;
        leftBackPos = leftBackPos - distance;
        rightBackPos = rightBackPos + distance;

        leftFront.setTargetPosition(leftFrontPos);
        rightFront.setTargetPosition(rightFrontPos);
        leftBack.setTargetPosition(leftBackPos);
        rightBack.setTargetPosition(rightBackPos);


        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setPower(speed);
        rightFront.setPower(speed);
        leftBack.setPower(speed);
        rightBack.setPower(speed);


        while (opModeIsActive() && leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy() && rightBack.isBusy()) {
            idle();
        }
    }


    private void custom(int leftFrontTarget, int rightFrontTarget, int leftBackTarget, int rightBackTarget, double speed  ) {
        leftFrontPos += leftFrontTarget;
        rightFrontPos += rightFrontTarget;
        leftBackPos += leftBackTarget;
        rightBackPos += rightBackTarget;

        leftFront.setTargetPosition(leftFrontPos);
        rightFront.setTargetPosition(rightFrontPos);
        leftBack.setTargetPosition(leftBackPos);
        rightBack.setTargetPosition(rightBackPos);

        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftFront.setPower(speed);
        rightFront.setPower(speed);
        leftBack.setPower(speed);
        rightBack.setPower(speed);

        while (opModeIsActive() && leftFront.isBusy() && rightFront.isBusy() && leftBack.isBusy() && rightBack.isBusy()) {
            idle();
        }
    }
}
