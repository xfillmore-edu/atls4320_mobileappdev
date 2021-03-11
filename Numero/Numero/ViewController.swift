//
//  ViewController.swift
//  Numero
//
//  Created by Xuedan on 3/11/21.
//

import UIKit

class ViewController: UIViewController {
    
    var numeros = DataHandler()
    @IBOutlet weak var numberInputField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // applies to using table view delegate...?
        numeros.onDataUpdate = {[weak self] (data: Fact) in self?.render()}
        
        // dismiss keyboard when tapping anywhere on view
        // https://stackoverflow.com/a/27079103
        let externaltap = UITapGestureRecognizer(target: self, action: #selector(UIInputViewController.dismissKeyboard))
        view.addGestureRecognizer(externaltap)

    }
    
    @objc func dismissKeyboard() {
        view.endEditing(true)
    }

    @IBAction func sendAPIquery(_ sender: UIButton) {
        // linked to L37'5 60 button
        
        if numberInputField.text != "" {
            let num = Int(numberInputField.text!)
            numeros.reqJSON(num!)

            // requesting data and parsing too closely together...?

//            let alert = UIAlertController(title: "Number Fact:", message: numeros.fFetch(), preferredStyle: .alert)
//            let action1 = UIAlertAction(title: "Cool", style: .default, handler: nil)
//            alert.addAction(action1)
//
//            present(alert, animated: true, completion: nil)
        }
        
        
        
    }
    
    func render() {
        let alert = UIAlertController(title: "Number Fact:", message: numeros.fFetch(), preferredStyle: .alert)
        let action1 = UIAlertAction(title: "Cool", style: .default, handler: nil)
        alert.addAction(action1)

        present(alert, animated: true, completion: nil)
    }
    
}

