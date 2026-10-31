
BUILD_FOLDER="build/install/api-gateway/bin"

if [[ -d $BUILD_FOLDER ]]
then
  bash $BUILD_FOLDER/stop || exit
  gradle clean iA
  # cd $BUILD_FOLDER || exit
  # bash $BUILD_FOLDER/start
else
  gradle clean iA
  # cd $BUILD_FOLDER || exit
  # bash $BUILD_FOLDER/start
fi