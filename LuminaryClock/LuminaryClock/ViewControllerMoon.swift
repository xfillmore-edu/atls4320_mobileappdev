//
//  ViewControllerMoon.swift
//  LuminaryClock
//
//  Created by Xuedan on 2/4/21.
//  Copyright © 2021 Xuedan. All rights reserved.
//
// references - about every page in Apple's documentation on DatePickers and relevant content


import UIKit

// no DatePicker protocols/delegates...
class ViewControllerMoon: UIViewController {
    
    // 1 lunar cycle is 27 days, 7 hours, 43 mins (27.321527778 days)
    let cycle = 27.321527778
    let phases = ["New Moon", "Waxing Crescent", "First Quarter Moon", "Waxing Gibbous", "Full Moon", "Waning Gibbous", "Third Quarter Moon", "Waning Crescent"]
//    @IBOutlet weak var dPicker: UIDatePicker!
    @IBOutlet weak var labelMoonPhase: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()

//        dPicker.datePickerMode = UIDatePicker.Mode.date
    }
    
    @IBAction func pickDate(_ sender: UIDatePicker) {
        // new moon 11 February 19:06 UTC (12:06 MST)
        // full moon at 13.66 days, 1st quarter 6.73, 3rd quarter 20.49
        
//        let _date:Date = dPicker.date
//
//        let dateformat = DateFormatter()
//        dateformat.dateFormat = "dd-MM-yyyy"
//        labelMoonPhase.text = dateformat.string(from:_date)
//
//        // estimate moon phase based on time difference from reference date
//        // grows less accurate further from reference date
//        var phase = ""
//        let referencedate:Date = dateformat.date(from:"11-02-2021")!
//        // DateInterval doesn't work for negative time difference
//        let difference = _date - referencedate
//        if (difference%27 == 0) {
//            phase = phases[0]
//        }
//        else if (difference%13 == 0) {
//            phase = phases[5]
//        }
//        else if (difference%7 == 0) {
//            // quarter moon
//        }
//         labelMoonPhase.text = "The moon will be a \(phase) on that day."
        
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
