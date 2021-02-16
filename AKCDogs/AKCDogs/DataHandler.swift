//
//  DataHandler.swift
//  AKCDogs
//
//  Created by Xuedan on 2/15/21.
//

import Foundation

class DataHandler {
    var akcdoglist = [DogGroup]()
    
    func letTheDogsOut(from loc: String) {
        if let path = Bundle.main.url(forResource: loc, withExtension: "plist") {
            let plistdecoder = PropertyListDecoder()
            
            do {
                let dat = try Data(contentsOf: path)
                akcdoglist = try plistdecoder.decode([DogGroup].self, from: dat)
            }
            catch {
                print (error)
            }
        }
    }
    
    func fetchGroups() -> [String] {
        var groups = [String]()
        for g in akcdoglist {
            groups.append(g.akcgroup)
        }
        return groups
    }
    
    func fetchDogs(index: Int) -> [String] {
        return akcdoglist[index].registeredbreeds
    }
    
    func registerBreed(index: Int, breed: String, location: Int) {
        // variable.sort(by: $0 < $1) // shorthand sorts alphabetically
        akcdoglist[index].registeredbreeds.insert(breed, at: location)
    }
    
    func unregisterBreed(index: Int, iloc: Int) {
        akcdoglist[index].registeredbreeds.remove(at: iloc)
    }
}
