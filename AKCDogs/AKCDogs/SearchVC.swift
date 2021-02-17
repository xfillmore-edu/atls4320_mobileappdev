//
//  SearchVC.swift
//  AKCDogs
//
//  Created by Xuedan on 2/16/21.
//

import UIKit

// view controller without associated view
class SearchVC: UITableViewController, UISearchResultsUpdating {
    
    var fullkennel = [String]()
    var query = [String]()
    
    func updateSearchResults(for searchController: UISearchController) {
        let querystr = searchController.searchBar.text
        query.removeAll(keepingCapacity: true)
        if querystr?.isEmpty == false {
            // closure called for each item to check match against query string
            let _filter: (String) -> Bool = { name in
                let range = name.range(of: querystr!, options: .caseInsensitive)
                return range != nil // returns true if match otherwise false
            }
            let matches = fullkennel.filter(_filter)
            query.append(contentsOf: matches)
        }
        
        tableView.reloadData()
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "detailCellIdentifier")
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return query.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "detailCellIdentifier", for: indexPath)

        cell.textLabel?.text = query[indexPath.row]

        return cell
    }

}
