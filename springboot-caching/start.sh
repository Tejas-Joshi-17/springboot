
BUILD_FOLDER="build/install/springboot-caching/bin"

if [[ -d $BUILD_FOLDER ]]
then
  bash $BUILD_FOLDER/stop || exit 1;
  gradle clean iA
  bash $BUILD_FOLDER/start || exit 1;
  # cd $BUILD_FOLDER || exit
  # bash start
else
  gradle clean iA
  bash $BUILD_FOLDER/start || exit 1;
  # cd $BUILD_FOLDER || exit
  # bash start
fi