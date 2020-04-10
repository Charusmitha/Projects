//
//  DashboardViewController.swift
//  AllRecipes
//
//  Created by Charusmitha Deshpande on 12/6/19.
//  Copyright © 2019 Charusmitha Deshpande. All rights reserved.
//

import UIKit
import FirebaseAuth

class DashboardViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    

   
    @IBAction func logoutUser(_ sender: Any) {
        let firebaseAuth = Auth.auth()
        do {
            try firebaseAuth.signOut()
            KeyChainService().keyChain.delete("uid")
        } catch let signOutError as NSError {
            print ("Error signing out: %@", signOutError)
            return
        }
        KeyChainService().keyChain.delete("uid")
        self.navigationController?.popViewController(animated: true)
    }
    
    
    @IBAction func goHome(_ sender: Any) {
        performSegue(withIdentifier: "homeSegue", sender: self)
    }
    
    
    @IBAction func goToFavourites(_ sender: Any) {
        performSegue(withIdentifier: "favouriteSegue", sender: self)
    }
    
}
