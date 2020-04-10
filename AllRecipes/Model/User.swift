//
//  User.swift
//  AllRecipes
//
//  Created by Charusmitha Deshpande on 12/7/19.
//  Copyright © 2019 Charusmitha Deshpande. All rights reserved.
//

import Foundation
import RealmSwift

class User: Object{
    @objc dynamic var uid: String = ""
    @objc dynamic var name: String = ""
    @objc dynamic var email: String = ""
    
    override static func primaryKey() -> String? {
        return "uid"
    }
}
