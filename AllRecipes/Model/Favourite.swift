//
//  Favourites.swift
//  AllRecipes
//
//  Created by Charusmitha Deshpande on 12/8/19.
//  Copyright © 2019 Charusmitha Deshpande. All rights reserved.
//

import Foundation
import RealmSwift

class Favourite: Object{
    @objc dynamic var uid: String = ""
    @objc dynamic var recipeIcon: String = ""
    @objc dynamic var recipeName: String = ""
    
    override static func primaryKey() -> String? {
        return "recipeName"
    }
}
