//
//  SnowdexTableVC.swift
//  Snowflakes
//
//  Main Table View for app
//

import UIKit

class SnowdexTableVC: UITableViewController {
    var snow = [Snowflake]()
    var weather = DataHandler()

    override func viewDidLoad() {
        super.viewDidLoad()

        // Preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Display Edit button in the navigation bar
         self.navigationItem.leftBarButtonItem = self.editButtonItem
        
        // assign closure to dataupdate
        weather.onDataUpdate = {[weak self] (data: [Snowflake]) in self?.render()}
        weather.dbInit()
    }

    // MARK: - Table view data source
    
    func render() {
        snow = weather.getSnow()
        tableView.reloadData()
    }

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return snow.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "tableCell", for: indexPath)
        let flake = snow[indexPath.row]
        cell.textLabel!.text = flake.type

        return cell
    }

    // Support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        return true
    }

    // Support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            if let lattice = snow[indexPath.row].id {
                weather.shovel(crystalLattice: lattice)
            }
        }
    }

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */


    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // segueAdd (to NewSnowVC)
        // segueDetail (to SnowscopeVC)
        
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
        
//        if segue.destination == "SnowscopeVC" {
//            print('preparing for segueDetail')
//        }
    }
    
    @IBAction func unwindSegue(segue: UIStoryboardSegue) {
        // segueCancel, segueDone
        // segueSave
        
        if segue.identifier == "segueSave" {
            let src = segue.source as! NewSnowVC
            if (src.sfType.isEmpty == false)
                && (src.sfSize.isEmpty == false)
                && (src.sfDesc.isEmpty == false) {
                //
                weather.snowfall(type: src.sfType, size: src.sfSize, temperature: src.sfTemp, description: src.sfDesc)
            }
            
        }
    }


}
