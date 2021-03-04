//
//  DetailViewController.swift
//  Balloons
//
//  Created by Xuedan on 3/4/21.
//

import UIKit

class DetailViewController: UIViewController {
    
    var selectedImage : String?
    @IBOutlet weak var imageView: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        if let selected = selectedImage {
            imageView.image = UIImage(named: selected)
        }
    }
    
    @IBAction func actionButtonPressed(_ sender: UIBarButtonItem) {
        var imgArray = [UIImage]()
        imgArray.append(imageView.image!)
        
        let sharing = UIActivityViewController(activityItems: imgArray, applicationActivities: nil)
        
        sharing.modalPresentationStyle = .popover
        sharing.popoverPresentationController?.barButtonItem = sender
        
        present(sharing, animated: true, completion: nil)
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
