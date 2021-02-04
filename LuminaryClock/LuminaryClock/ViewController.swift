//
//  ViewController.swift
//  LuminaryClock
//
//  Created by Xuedan on 2/4/21.
//  Copyright © 2021 Xuedan. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    @IBOutlet weak var starPicker: UIPickerView!
    @IBOutlet weak var labelMessage: UILabel!
    var constellation = [String]()
    var stars = [String]()
    // var designations = [String]()
    
    let starsfile = "constellations"
    var pickerContent = DataLoader()
    
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 2
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if component == 0 {
            return constellation.count
        }
        else {
            return stars.count
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if component == 0 {
            return constellation[row]
        }
        else {
            return stars[row]
        }
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        if component == 0 {
            stars = pickerContent.getStars(index: row)
            starPicker.reloadComponent(1)
            starPicker.selectRow(0, inComponent: 1, animated: true)
        }
        
        let scr = pickerView.selectedRow(inComponent: 0)
        let ssr = pickerView.selectedRow(inComponent: 1)
        labelMessage.text = "\(stars[ssr]) is in \(constellation[scr])"
        // labelMessage.text = "\(stars[ssr]) is designated as \(designations[ssr]) in \(constellation[scr])"
    }
    

    override func viewDidLoad() {
        super.viewDidLoad()
        
        pickerContent.loadData(filename: starsfile)
        constellation = pickerContent.buildConstellations()
        stars = pickerContent.getStars(index: 0)
        // designations = pickerContent.getDesignations(index: 0)
    }


}

