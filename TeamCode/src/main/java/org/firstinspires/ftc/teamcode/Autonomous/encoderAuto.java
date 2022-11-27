package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@Autonomous(name = "encoderAuto")

public class encoderAuto extends LinearOpMode {
    private DcMotor rightFront = null;
    private DcMotor rightBack;
    private DcMotor leftFront;
    private DcMotor leftBack;

    int leftFrontPos = 0;
    int rightFrontPos = 0;
    int leftBackPos = 0;
    int rightBackPos = 0;



    @Override
    public void runOpMode() throws InterruptedException {
        rightFront = hardwareMap.dcMotor.get("rightFront");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        leftFront = hardwareMap.dcMotor.get("leftFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");

        rightBack.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);




        waitForStart();












        // function move(distance)
        leftFront.setTargetPosition(var);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setPower(.5);

        rightFront.setTargetPosition(distance);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setPower(.5);

        leftBack.setTargetPosition(distance);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setPower(.5);

        rightBack.setTargetPosition(distance);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setPower(.5);




        forward(30,.5);







    }

    private void forward(int target, double speed  ) {

        
        rightBackPos += target;

        leftFront.setTargetPosition(var);


        rightBack.setTargetPosition(rightBackPos);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setPower(.5);

    }


}
