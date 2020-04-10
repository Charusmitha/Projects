//
//  RecipeDetails.swift
//  AllRecipes
//
//  Created by Charusmitha Deshpande on 12/8/19.
//  Copyright © 2019 Charusmitha Deshpande. All rights reserved.
//

import Foundation
import RealmSwift

class RecipeDetails: Object{
    @objc dynamic var recipeName : String = ""
    @objc dynamic var recipeIcon : String = ""
    @objc dynamic var recipeTime : String = ""
    @objc dynamic var recipeServing : String = ""
    @objc dynamic var recipeInstructions : String = ""
    
    override static func primaryKey() -> String? {
        return "recipeName"
    }
}
