//
//  NewSnowVC.swift
//  Snowflakes
//
//  Add new DB records view
//

import UIKit

class NewSnowVC: UIViewController {
    
    // declare vars -- String checked against nil for validity
    var sfType = String()
    var sfSize = String()
    var sfTemp = 0
    var sfDesc = String()
    
    @IBOutlet weak var sfTypeTxtField: UITextField!
    @IBOutlet weak var sfSizeSegCtrl: UISegmentedControl!
    @IBOutlet weak var sfTempStepper: UIStepper!
    @IBOutlet weak var sfTempTxtLbl: UILabel!
    @IBOutlet weak var sfDescTxtObj: UITextView!

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    @IBAction func sfTempUpdate(_ sender: UIStepper) {
        sfTempTxtLbl.text = String(Int(sender.value))
    }
    
    
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
        if segue.identifier == "segueSave" {
            if (sfTypeTxtField.hasText)
                && (sfSizeSegCtrl.isSelected)
                && (sfDescTxtObj.hasText) {
                //
                sfType = sfTypeTxtField.text!
                sfSize = sfSizeSegCtrl.selectedSegmentIndex.description
                sfTemp = Int(sfTempStepper.value)
                sfDesc = sfDescTxtObj.text!
                
            }
        }
    }

}
