# Uncomment the next line to define a global platform for your project
# platform :ios, '10.0'

target 'Snowflakes' do
  # Comment the next line if you don't want to use dynamic frameworks
  use_frameworks!

  # Pods for Snowflakes
pod 'Firebase/Firestore'
pod 'FirebaseFirestoreSwift', '~> 7.0-beta'
pod 'FirebaseUI/Auth'
pod 'FirebaseUI/Google'

end

post_install do |installer|
  installer.pods_project.targets.each do |target|
    target.build_configurations.each do |config|
      config.build_settings.delete 'IPHONEOS_DEPLOYMENT_TARGET'
    end
  end
end
