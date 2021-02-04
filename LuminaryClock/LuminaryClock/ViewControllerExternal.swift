//
//  ViewControllerExternal.swift
//  LuminaryClock
//
//  Created by Xuedan on 2/4/21.
//  Copyright © 2021 Xuedan. All rights reserved.
//

import UIKit

class ViewControllerExternal: UIViewController {

    @IBAction func openExternalApp(_ sender: UIButton) {
        // I don't know if this is the correct URL scheme for Night Sky because I was unable to find
        //   it, and I can't install it on the simulator to test if it actually opens.
        //   The error I get when it attempts to launch is "This app is not allowed to query
        //   for scheme nightsky" so I don't remember if this was the error associated with an
        //   incorrect scheme or an uninstalled app.
        let ns : URL = URL(string: "nightsky://")!
        
        if (UIApplication.shared.canOpenURL(ns)) {
            UIApplication.shared.open(ns, options: [:], completionHandler: nil)
        }
        else {
            UIApplication.shared.open(URL(string:"https://apps.apple.com/us/app/night-sky/id475772902")!, options:[:], completionHandler: nil)
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
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
