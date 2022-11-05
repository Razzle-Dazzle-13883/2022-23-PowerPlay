package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@Autonomous(name = "redJunction")

public class redJunction extends LinearOpMode {
    private DcMotor rightFront;
    private DcMotor rightBack;
    private DcMotor leftFront;
    private DcMotor leftBack;


    @Override
    public void runOpMode() throws InterruptedException {
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");

        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();


        rightFront.setPower(0.0);
        rightBack.setPower(0.0);
        leftFront.setPower(0.0);
        leftBack.setPower(0.0);

        sleep(500);


        leftFront.setPower(-.15);
        rightFront.setPower(.15);
        rightBack.setPower(-.15);
        leftBack.setPower(.15);

        sleep(2000);

        leftFront.setPower(.15);
        rightFront.setPower(.15);
        rightBack.setPower(.15);
        leftBack.setPower(.15);

        sleep(1000);

        leftFront.setPower(-.2);
        rightFront.setPower(-.2);
        rightBack.setPower(-.2);
        leftBack.setPower(-.2);

        sleep(1000);

        leftFront.setPower(-.2);
        rightFront.setPower(.2);
        rightBack.setPower(-.2);
        leftBack.setPower(.2);

        sleep(1750);

    }
}