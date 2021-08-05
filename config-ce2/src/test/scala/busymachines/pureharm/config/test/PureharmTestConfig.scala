/*
 * Copyright 2019 BusyMachines
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package busymachines.pureharm.config.test

/** @author
  *   Lorand Szakacs, https://github.com/lorandszakacs
  * @since 16
  *   Jun 2019
  */

final private[test] case class PureharmTestConfig(
  phantomInt:            PhantomInt,
  phantomString:         PhantomString,
  phantomBoolean:        PhantomBoolean,
  phantomList:           PhantomList,
  phantomSet:            PhantomSet,
  phantomFiniteDuration: PhantomFiniteDuration,
  phantomDuration:       PhantomDuration,
  safePhantomInt:        SafePhantomInt,
)

import busymachines.pureharm.config._

private[test] object PureharmTestConfig extends ConfigLoader[PureharmTestConfig] {
  implicit val throwableShow: cats.Show[Throwable] = cats.Show.fromToString[Throwable]
  import busymachines.pureharm.config.implicits._ //needed for all sprout implicits
  import cats.effect._

  implicit override val configReader: ConfigReader[PureharmTestConfig] =
    semiauto.deriveReader[PureharmTestConfig]

  def testConfig[F[_]: Sync]: F[PureharmTestConfig] =
    this.load("pureharm.config.test")
}
