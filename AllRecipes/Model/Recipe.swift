//
//  Recipe.swift
//  AllRecipes
//
//  Created by Charusmitha Deshpande on 12/7/19.
//  Copyright © 2019 Charusmitha Deshpande. All rights reserved.
//

import Foundation
import RealmSwift

class Recipe: Object{
    @objc dynamic var recipeId : String = ""
    @objc dynamic var recipeIcon : String = ""
    @objc dynamic var recipeName : String = ""
    
    override static func primaryKey() -> String? {
        return "recipeId"
    }
}


