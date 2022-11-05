package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@Autonomous(name = "encoderAuto")

public class encoderAuto extends LinearOpMode {
    private DcMotor rightFront;
    private DcMotor rightBack;
    private DcMotor leftFront;
    private DcMotor leftBack;

    double rightFrontPos;
    double rightBackPos;
    double leftFrontPos;
    double leftBackPos;


    @Override
    public void runOpMode() throws InterruptedException {
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");

        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        


        waitForStart();



    }
}