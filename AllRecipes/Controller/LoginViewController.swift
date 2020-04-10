//
//  ViewController.swift
//  AllRecipes
//
//  Created by Charusmitha Deshpande on 12/6/19.
//  Copyright © 2019 Charusmitha Deshpande. All rights reserved.
//

import UIKit
import FirebaseAuth

class LoginViewController: UIViewController {

    @IBOutlet weak var txtEmail: UITextField!
    
    @IBOutlet weak var txtPassword: UITextField!
    
    @IBOutlet weak var lblWelcome: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
    }
    
    override func viewDidAppear(_ animated: Bool) {
        let keyChain = KeyChainService().keyChain
        if keyChain.get("uid") != nil {
            performSegue(withIdentifier: "dashboardSegue", sender: self)
        }
    }
    
    func AddKeyChainAfterLogin(uid: String){
        let keyChain = KeyChainService().keyChain
        keyChain.set(uid, forKey: "uid")
    }


    @IBAction func loginUser(_ sender: Any) {
        let email = txtEmail.text!
        let password = txtPassword.text!
        
        if(txtEmail.text! == "" || txtPassword.text! == ""){
            lblWelcome.text = " Please enter valid Email/Password"
            return
        }
        
        if email.isEmail == false {
            lblWelcome.text = " Please enter valid Email"
            return
        }
        
        Auth.auth().signIn(withEmail: email, password: password) { [weak self] authResult, error in
            guard let strongSelf = self else { return }
           
            
            if(error == nil){
                // go to logged in page
                strongSelf.AddKeyChainAfterLogin(uid: Auth.auth().currentUser!.uid)
                strongSelf.performSegue(withIdentifier: "dashboardSegue", sender: strongSelf)
                
            }else{
                strongSelf.lblWelcome.text = error?.localizedDescription
            }
        }
    }
    
    @IBAction func registerUser(_ sender: Any) {
        
        performSegue(withIdentifier: "registerSegue", sender: self)
    }
}

