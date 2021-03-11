//
//  Fact.swift
//

import Foundation

struct Fact : Decodable {
    let found : Bool
    let text : String
    let number : Float
}

struct FactRaw : Decodable {
    var content = [Fact]()
}
