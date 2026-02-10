
BUILD_FOLDER="build/install/spring-jpa/bin"

if [[ -d $BUILD_FOLDER ]]
then
  cd $BUILD_FOLDER || exit
  bash stop
  cd ../../../../ || exit
  gradle clean iA
  # cd $BUILD_FOLDER || exit
  # bash $BUILD_FOLDER/start
else
  gradle clean iA
  # cd $BUILD_FOLDER || exit
  # bash $BUILD_FOLDER/start
fi