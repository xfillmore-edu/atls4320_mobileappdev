//
//  StarMap.swift
//  LuminaryClock
//
//  Created by Xuedan on 2/4/21.
//  Copyright © 2021 Xuedan. All rights reserved.
//

import Foundation

struct StarMap: Decodable {
    let constellation: String
    let memberstars: [String]
    let stardesignations: [String]
}
