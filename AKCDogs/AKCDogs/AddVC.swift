//
//  AddVC.swift
//  AKCDogs
//
//  Created by Xuedan on 2/16/21.
//

import UIKit

class AddVC: UIViewController {

    @IBOutlet weak var breedInput: UITextField!
    
    var newDog = String()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    // called by "Cancel" and "Save" but "Cancel" shouldn't do anything
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "unwindSaveSegue" {
            if breedInput.text?.isEmpty == false {
                newDog = breedInput.text!
            }
        }
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
