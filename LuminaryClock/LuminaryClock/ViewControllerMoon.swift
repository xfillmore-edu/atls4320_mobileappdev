//
//  ViewControllerMoon.swift
//  LuminaryClock
//
//  Created by Xuedan on 2/4/21.
//  Copyright © 2021 Xuedan. All rights reserved.
//

import UIKit

class ViewControllerMoon: UIViewController {
    
    // 1 lunar cycle is 27 days, 7 hours, 43 mins (27.321527778 days)
    let cycle = 27.321527778
    let phases = ["New Moon", "Waxing Crescent", "First Quarter", "Waxing Gibbous", "Full Moon", "Waning Gibbous", "Third Quarter", "Waning Crescent"]
    @IBOutlet weak var datePicker: UIDatePicker!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        datePicker.datePickerMode = UIDatePicker.Mode.date
//        datePicker.date = _date
    }
    
    @IBAction func pickDate(_ sender: UIDatePicker) {
        // new moon 11 February 19:06 UTC (12:06 MST)

    }
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
