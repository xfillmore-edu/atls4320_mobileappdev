//
//  DataHandler.swift
//  Snowflakes
//
//  Firestore Data Manager
//

import Foundation
import FirebaseFirestore
import FirebaseFirestoreSwift

class DataHandler {
    let db = Firestore.firestore().collection("snowflakes")
    
    var crystals = [Snowflake]()
    
    var onDataUpdate: ((_ data: [Snowflake]) -> Void)?
    
    // initialize db
    func dbInit() {
        db.addSnapshotListener {querySnapshot, error in
            guard let docs = querySnapshot?.documents else {
                print("DB snapshot fetch error: \(error!)")
                return
            }
            
            self.crystals = docs.compactMap {d->Snowflake? in
                return try? d.data(as: Snowflake.self)
            }
            self.onDataUpdate?(self.crystals)
        }
    }
    
    // fetch db data
    func getSnow() -> [Snowflake] {
        return crystals
    }
    
    // add document to db
    func snowfall(type t: String, size s: String, temperature temp: Int, description d: String) {
        let input: [String:Any] = ["type":t, "size":s, "temperature":temp, "description":d]
        
        // create new document with auto generated id
        var ref: DocumentReference? = nil
        ref = db.addDocument(data: input) {error in
            if let err = error {
                print("DB error adding: \(err)")
            }
            else {
                print("DB document added with ID \(ref!.documentID)")
            }
        }
    }
    
    func shovel(crystalLattice id: String) {
        db.document(id).delete() {error in
            if let err = error {
                print("DB error removing: \(err)")
            }
            else {
                print("DB removed document successfully")
            }
        }
    }
    
}
