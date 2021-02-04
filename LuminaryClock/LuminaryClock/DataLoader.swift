//
//  DataLoader.swift
//  LuminaryClock
//
//  Created by Xuedan on 2/4/21.
//  Copyright © 2021 Xuedan. All rights reserved.
//

import Foundation

class DataLoader {
    var data = [StarMap]()
    
    func loadData(filename: String) {
        if let path = Bundle.main.url(forResource: filename, withExtension: "plist") {
            let plistdecoder = PropertyListDecoder()
            
            do {
                let d = try Data(contentsOf: path)
                data = try plistdecoder.decode([StarMap].self, from: d)
            }
            catch {
                print ("Error loading constellations.")
            }
        }
    }
    
    func buildConstellations() -> [String] {
        var constellations = [String]()
        for c in data {
            constellations.append(c.constellation)
        }
        return constellations
    }
    
    func getStars(index: Int) -> [String] {
        return data[index].memberstars
    }
    
    func getDesignations(index: Int) -> [String] {
        return data[index].stardesignations
    }
}
