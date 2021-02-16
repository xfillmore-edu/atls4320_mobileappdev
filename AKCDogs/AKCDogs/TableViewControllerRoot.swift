//
//  TableViewControllerRoot.swift
//  AKCDogs
//
//  Created by Xuedan on 2/16/21.
//

import UIKit

class TableViewControllerRoot: UITableViewController {
    
    var kennel = DataHandler()
    var groupslist = [String]()
    let datasource = "dogbreeds"

    override func viewDidLoad() {
        super.viewDidLoad()

        kennel.letTheDogsOut(from: datasource)
        groupslist = kennel.fetchGroups()
        
        
        
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return groupslist.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "rootCellIdentifier", for: indexPath)

        cell.textLabel?.text = groupslist[indexPath.row]

        return cell
    }

    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
        
        if segue.identifier == "groupDetailSegue" {
            // as? returns nil if fails, returns instance of TableViewController if succeeds
            // as! forces downcast and fail would cause app crash
            if let nextview = segue.destination as? TableViewControllerDetail {
                if let indexPath = tableView.indexPath(for: (sender as? UITableViewCell)!) {
                    // set data for destination controller
                    nextview.title = groupslist[indexPath.row]
                    nextview.kennel = kennel // better and faster to load existing data if unchanged
                    nextview.groupnum = indexPath.row
                }
            }
        }
//        else if segue.identifier == "" {
//            let prevview = segue.destination as! TableViewControllerRoot
//            let e
//        }
    }


}
