//
//  Snowflake.swift
//  Snowflakes
//
//  Data Struct
//

import Foundation
import FirebaseFirestoreSwift

struct Snowflake : Codable, Identifiable {
    @DocumentID var id: String?
    
    var type : String = ""
    var size : String = ""
    var temperature : Int = 0
    var description : String = ""
}
