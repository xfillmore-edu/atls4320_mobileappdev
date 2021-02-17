//
//  TableViewControllerDetail.swift
//  AKCDogs
//
//  Created by Xuedan on 2/16/21.
//

import UIKit

class TableViewControllerDetail: UITableViewController {
    
    var kennel = DataHandler()
    var groupnum = 0
    var breedlist = [String]()
    
    var searchable = UISearchController()
    
    override func viewWillAppear(_ animated: Bool) {
        breedlist = kennel.fetchDogs(index: groupnum)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        
        // search controller
        let results = SearchVC() // instance of Search View Controller
        results.fullkennel = kennel.fetchDogs(index: groupnum)
        searchable = UISearchController(searchResultsController: results)
        
        searchable.searchBar.placeholder = "Woof?"
        searchable.searchBar.sizeToFit()
        tableView.tableHeaderView = searchable.searchBar
        searchable.searchResultsUpdater = results

    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return breedlist.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "detailCellIdentifier", for: indexPath)

        cell.textLabel?.text =  breedlist[indexPath.row]

        return cell
    }

    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }

    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete item from the array
            breedlist.remove(at: indexPath.row)
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
            // Delete from data model instance
            kennel.unregisterBreed(index: groupnum, iloc: indexPath.row)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }

    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return false
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
    }

    // MARK: - Navigation

    @IBAction func unwindSegue(_ segue : UIStoryboardSegue) {
        if segue.identifier == "unwindSaveSegue" {
            if let source = segue.source as? AddVC {
                if source.newDog.isEmpty == false {
                    kennel.registerBreed(index: groupnum, breed: source.newDog, location: breedlist.count)
                    breedlist.append(source.newDog)
                    
                    tableView.reloadData()
                }
            }
        }
    }

}
