//
//  DataHandler.swift
//  Nursery
//
//  Created by Xuedan on 5/3/21.
//

import Foundation

class DataHandler {
    var allRhymes = [Rhymes]()
    
    func loadData(filename: String) {
        if let fpath = Bundle.main.url(forResource: filename, withExtension: "plist") {
            let plistdecoder = PropertyListDecoder()
            do {
                let contents = try Data(contentsOf: fpath)
                allRhymes = try plistdecoder.decode([Rhymes].self, from: contents)
            }
            catch {
                print(error)
            }
        }
    }
    
    func getTitles() -> [String] {
        var rTitle = [String]()
        for r in allRhymes {
            rTitle.append(r.title)
        }
        return rTitle
    }
    
    func getContents(index: Int) -> String {
        return allRhymes[index].content
    }
}
