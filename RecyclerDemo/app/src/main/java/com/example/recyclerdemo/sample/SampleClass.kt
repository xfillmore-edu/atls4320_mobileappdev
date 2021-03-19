package com.example.recyclerdemo.sample

import com.example.recyclerdemo.R
import com.example.recyclerdemo.model.Bird

// singleton ...
object SampleClass {
    val birdList = ArrayList<Bird>()

    init {
        birdList.add(Bird("African Crowned Eagle", R.drawable.birds_africancrownedeagle))
        birdList.add(Bird("Bearded Vulture", R.drawable.birds_beardedvulture))
        birdList.add(Bird("Blakiston's Fish Owl", R.drawable.birds_blakistonsfishowl))
        birdList.add(Bird("Eurasian Eagle Owl", R.drawable.birds_eurasianeagleowl))
        birdList.add(Bird("Golden Eaglee", R.drawable.birds_goldeneagle))
        birdList.add(Bird("Harpy Eagle", R.drawable.birds_harpyeagle))
        birdList.add(Bird("Lappet Faced Vulture", R.drawable.birds_lappetfacedvulture))
        birdList.add(Bird("Martial Eagle", R.drawable.birds_martialeagle))
        birdList.add(Bird("Philippine Eagle", R.drawable.birds_philippineeagle))
        birdList.add(Bird("Stellar's Sea Eagle", R.drawable.birds_stellarsseaeagle))
    }
}

// array vs list
// array - mutable, fixed size (items within can be changed)
// list  - either array list or linked list