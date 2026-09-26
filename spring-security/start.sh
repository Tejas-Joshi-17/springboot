
BUILD_FOLDER="build/install/spring-security/bin"

if [[ -d $BUILD_FOLDER ]]
then
  bash  $BUILD_FOLDER/stop
  gradle clean iA
  # cd $BUILD_FOLDER || exit
  # bash $BUILD_FOLDER/start
else
  gradle clean iA
  # cd $BUILD_FOLDER || exit
  # bash $BUILD_FOLDER/start
fi