//
//  SecondaryViewController.swift
//  Nursery
//
//  Created by Xuedan on 5/3/21.
//

import UIKit

class SecondaryViewController: UIViewController {
    
    @IBOutlet weak var rcLabel: UILabel!
    var rc : String?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        if let rContent = rc {
            loadContent(rContent)
        }
    }
    
    func loadContent(_ content: String) {
        rcLabel.text = content
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
