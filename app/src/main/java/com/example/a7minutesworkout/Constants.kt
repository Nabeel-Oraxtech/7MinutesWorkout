package com.example.a7minutesworkout

object Constants {
    fun defaultExerciseList():ArrayList<ExerciseModel>{
        val exerciseList=ArrayList<ExerciseModel>()
        val jumpingJAcks=ExerciseModel(
            1,
            "Jumping Jacks",R.drawable.ic_jumping_jacks,
            false,
            false
        )
        exerciseList.add(jumpingJAcks)

        val abdominalCrunch=ExerciseModel(
            2,
            "Abdominal Crunch",R.drawable.ic_abdominal_crunch,
            false,
            false
        )
        exerciseList.add(abdominalCrunch)

        val highKneesRunning=ExerciseModel(
            3,
            "High Knees Running",R.drawable.ic_high_knees_running_in_place,
            false,
            false
        )
        exerciseList.add(highKneesRunning)

        val lunge=ExerciseModel(
            4,
            "Lunge",R.drawable.ic_lunge,
            false,
            false
        )
        exerciseList.add(lunge)

        val plank=ExerciseModel(
            5,
            "Plank",R.drawable.ic_plank,
            false,
            false
        )
        exerciseList.add(plank)

        val pushUp=ExerciseModel(
            6,
            "Push Up",R.drawable.ic_push_up,
            false,
            false
        )
        exerciseList.add(pushUp)

        val pushupRotation=ExerciseModel(
            7,
            "Pushup Rotation",R.drawable.ic_push_up_and_rotation,
            false,
            false
        )
        exerciseList.add(pushupRotation)

        val sidePkank=ExerciseModel(
            8,
            "Side Plank",R.drawable.ic_side_plank,
            false,
            false
        )
        exerciseList.add(sidePkank)

        val squat=ExerciseModel(
            9,
            "Squat",R.drawable.ic_squat,
            false,
            false
        )
        exerciseList.add(squat)

        val stepUp=ExerciseModel(
            10,
            "Step Up",R.drawable.ic_step_up_onto_chair,
            false,
            false
        )
        exerciseList.add(stepUp)

        val tricepsDip=ExerciseModel(
            11,
            "Triceps Dip",R.drawable.ic_triceps_dip_on_chair,
            false,
            false
        )
        exerciseList.add(tricepsDip)

        val wallSit=ExerciseModel(
            12,
            "Wall Sit",R.drawable.ic_wall_sit,
            false,
            false
        )
        exerciseList.add(wallSit)

        return exerciseList
    }
}