//
//  CollectionViewController.swift
//  Balloons
//
//  Created by Xuedan on 3/4/21.
//

import UIKit

private let reuseIdentifier = "CollectionCellIdentifier"

class CollectionViewController: UICollectionViewController {
        
    var mammalArray : [String] = ["z_bison", "z_alpaca", "z_beaver",
                                    "z_baboon", "z_bunny", "z_camel",
                                    "z_dog2", "z_elephants", "z_ermine",
                                    "z_giraffe", "z_goat", "z_hedgehog",
                                    "z_horse", "z_lion", "z_mouse",
                                    "z_orca", "z_ox", "z_panda", "z_pangolin",
                                    "z_pygmyjerboa", "z_raccoon", "z_siamang",
                                    "z_walrus", "z_whitetiger"]
    var birdArray : [String] = ["z_baldeagle", "z_barnowl", "z_birdnest2",
                                "z_bluebird", "z_cassowary", "z_cockatiel2",
                                "z_condor", "z_crane", "z_crow",
                                "z_flamingo", "z_hawk", "z_hornedowl",
                                "z_hornedpuffin", "z_kingfisher", "z_kiwi",
                                "z_ostrich", "z_paradiseflycatcher", "z_parakeet",
                                "z_parrot", "z_peacock", "z_pelican",
                                "z_rockhopperpenguin", "z_rooster", "z_shoebill",
                                "z_swan", "z_turkey", "z_emperorpenguin"]
    var arthropodArray : [String] = ["z_bee", "z_butterfly", "z_caterpillar",
                                    "z_centipede", "z_cicada", "z_cuckoowasp",
                                    "z_dragonfly", "z_fly", "z_moth",
                                    "z_prayingmantis", "z_spider2", "z_trilobite",
                                    "z_crab", "z_hermitcrab"]
    var fishmollusksArray : [String] = ["z_goldfish", "z_jellyfish", "z_koi",
                                "z_octopus", "z_snail", "z_squid",
                                "z_seahorse", "z_ray", "z_shark"]
    var reptileArray : [String] =  ["z_alligator", "z_cobra", "z_dragon",
                                    "z_dragon2", "z_frilledlizard", "z_iguana",
                                    "z_seaturtle2", "z_snake", "z_turtle"]
    var amphibianArray: [String] = ["z_axolotl", "z_frog"]
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        collectionView.collectionViewLayout = defineDisplayLayout()
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using [segue destinationViewController].
        // Pass the selected object to the new view controller.
    }
    */

    // MARK: UICollectionViewDataSource

    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        return 6
    }


    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        switch section {
            case 0:
                return mammalArray.count
            case 1:
                return amphibianArray.count
            case 2:
                return birdArray.count
            case 3:
                return reptileArray.count
            case 4:
                return fishmollusksArray.count
            case 5:
                return arthropodArray.count
            default:
                break
        }
        return 0
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! CollectionViewCell
    
        // Configure the cell
        var img : UIImage?
        switch indexPath.section {
            case 0:
                img = UIImage(named: mammalArray[indexPath.row])
            case 1:
                img = UIImage(named: amphibianArray[indexPath.row])
            case 2:
                img = UIImage(named: birdArray[indexPath.row])
            case 3:
                img = UIImage(named: reptileArray[indexPath.row])
            case 4:
                img = UIImage(named: fishmollusksArray[indexPath.row])
            case 5:
                img = UIImage(named: arthropodArray[indexPath.row])
            default:
                break
        }
        
        cell.imageView.image = img
    
        return cell
    }
    
    func defineDisplayLayout() -> UICollectionViewLayout {
        
        // can define different item sizes within same group
        
        // item size
        let iSize = NSCollectionLayoutSize(widthDimension: .fractionalWidth(1.0), heightDimension: .fractionalHeight(1.0))
        // layout item
        let iLayout = NSCollectionLayoutItem(layoutSize: iSize)
        iLayout.contentInsets = NSDirectionalEdgeInsets(top: 1, leading: 8, bottom: 1, trailing: 8)
        
        // group size
        let gSize = NSCollectionLayoutSize(widthDimension: .fractionalWidth(1.0), heightDimension: .fractionalHeight(0.2))
        // horizontally arranged group
        let gLayout = NSCollectionLayoutGroup.horizontal(layoutSize: gSize, subitem: iLayout, count: 3)
        
        // sections
        let sLayout = NSCollectionLayoutSection(group: gLayout)
        let hSize = NSCollectionLayoutSize(widthDimension: .fractionalWidth(1.0), heightDimension: .estimated(48))
        let fSize = NSCollectionLayoutSize(widthDimension: .fractionalWidth(1.0), heightDimension: .estimated(36))
        let sHeader = NSCollectionLayoutBoundarySupplementaryItem(layoutSize: hSize, elementKind: UICollectionView.elementKindSectionHeader, alignment: .top)
        let sFooter = NSCollectionLayoutBoundarySupplementaryItem(layoutSize: fSize, elementKind: UICollectionView.elementKindSectionFooter, alignment: .bottom)
        sLayout.boundarySupplementaryItems = [sHeader, sFooter]
        
        // layout object
        let layout = UICollectionViewCompositionalLayout(section: sLayout)
        
        return layout
    }
    
    override func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        var header = CollectionHeaderView()
        var footer = CollectionFooterView()
        
        if kind == UICollectionView.elementKindSectionHeader {
            header = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: "HeaderIdentifier", for: indexPath) as! CollectionHeaderView
            
            switch indexPath.section {
                case 0:
                    header.headerLabel.text = "Mammals"
                case 1:
                    header.headerLabel.text = "Amphibians"
                case 2:
                    header.headerLabel.text = "Birds"
                case 3:
                    header.headerLabel.text = "Reptiles"
                case 4:
                    header.headerLabel.text = "Fish and Mollusks"
                case 5:
                    header.headerLabel.text = "Arthropods"
                default:
                    header.headerLabel.text = ""
            }
        }
        else { // UICollectionView.elementKindSectionFooter
            footer = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: "FooterIdentifier", for: indexPath) as! CollectionFooterView
            
            switch indexPath.section {
                case 0:
                    footer.footerLabel.text = "They seem to be missing their hair..."
                case 1:
                    footer.footerLabel.text = "Check out that smooth amphibious skin!"
                case 2:
                    footer.footerLabel.text = "These guys just might fly away."
                case 3:
                    footer.footerLabel.text = "100% domesticated and harmless."
                case 4:
                    footer.footerLabel.text = "These sea critters don't actually go well with water."
                case 5:
                    footer.footerLabel.text = "Don't let the bed balloons pop!"
                default:
                    footer.footerLabel.text = ""
            }
        }
        
        return (kind==UICollectionView.elementKindSectionHeader) ? header : footer
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "SegueDetailIdentifier" {
            let indexPath = collectionView?.indexPath(for: sender as! CollectionViewCell)
            let detailViewController = segue.destination as! DetailViewController
            
            
            switch (indexPath?.section)! {
            case 0:
                detailViewController.selectedImage = mammalArray[(indexPath?.row)!]
            case 1:
                detailViewController.selectedImage = amphibianArray[(indexPath?.row)!]
            case 2:
                detailViewController.selectedImage = birdArray[(indexPath?.row)!]
            case 3:
                detailViewController.selectedImage = reptileArray[(indexPath?.row)!]
            case 4:
                detailViewController.selectedImage = fishmollusksArray[(indexPath?.row)!]
            case 5:
                detailViewController.selectedImage = arthropodArray[(indexPath?.row)!]
            default:
                detailViewController.selectedImage = ""
            }
            
        }
    }

    // MARK: UICollectionViewDelegate

    /*
    // Uncomment this method to specify if the specified item should be highlighted during tracking
    override func collectionView(_ collectionView: UICollectionView, shouldHighlightItemAt indexPath: IndexPath) -> Bool {
        return true
    }
    */

    /*
    // Uncomment this method to specify if the specified item should be selected
    override func collectionView(_ collectionView: UICollectionView, shouldSelectItemAt indexPath: IndexPath) -> Bool {
        return true
    }
    */

    /*
    // Uncomment these methods to specify if an action menu should be displayed for the specified item, and react to actions performed on the item
    override func collectionView(_ collectionView: UICollectionView, shouldShowMenuForItemAt indexPath: IndexPath) -> Bool {
        return false
    }

    override func collectionView(_ collectionView: UICollectionView, canPerformAction action: Selector, forItemAt indexPath: IndexPath, withSender sender: Any?) -> Bool {
        return false
    }

    override func collectionView(_ collectionView: UICollectionView, performAction action: Selector, forItemAt indexPath: IndexPath, withSender sender: Any?) {
    
    }
    */

}
