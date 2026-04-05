
BUILD_FOLDER="build/install/production-ready-feature/bin"

if [[ -d $BUILD_FOLDER ]]
then
  cd $BUILD_FOLDER || exit
  bash stop
  cd ../../../../ || exit
  gradle iA
  # cd $BUILD_FOLDER || exit
  # bash $BUILD_FOLDER/start
else
  gradle iA
  # cd $BUILD_FOLDER || exit
  # bash $BUILD_FOLDER/start
fi