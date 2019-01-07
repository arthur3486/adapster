/*
 * Copyright 2017 Arthur Ivanets, arthur.ivanets.l@gmail.com
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

package com.arthurivanets.sample.util

import com.arthurivanets.sample.model.Article
import com.arthurivanets.sample.model.FooterInfo
import com.arthurivanets.sample.model.Topic

object DataProvider {


    @JvmStatic val PLANETS_TOPICS = listOf(
        Topic(
            id = 1,
            name = "Sun",
            imageUrl = "https://static.bhphotovideo.com/explora/sites/default/files/styles/top_shot/public/ts-space-sun-and-solar-viewing-facts-versus-fiction.jpg?itok=gaBs6QMS"
        ),
        Topic(
            id = 2,
            name = "Mars",
            imageUrl = "https://www.eartheclipse.com/wp-content/uploads/2017/09/Mars-planet.jpg"
        ),
        Topic(
            id = 3,
            name = "Jupiter",
            imageUrl = "https://diariodeavisos.elespanol.com/wp-content/uploads/2018/07/fotonoticia_20180111122303_640.jpg"
        ),
        Topic(
            id = 4,
            name = "Mercury",
            imageUrl = "https://imgix.bustle.com/uploads/image/2018/3/21/cd0391ac-bcc8-4463-bc88-87189e984145-fotolia_95430604_subscription_monthly_m.jpg?w=970&h=582&fit=crop&crop=faces&auto=format&q=70"
        ),
        Topic(
            id = 5,
            name = "Earth",
            imageUrl = "http://www.slate.com/content/dam/slate/blogs/bad_astronomy/2016/07/21/epic_earth_oneyear.jpg.CROP.original-original.jpg"
        ),
        Topic(
            id = 6,
            name = "Saturn",
            imageUrl = "https://solarsystem.nasa.gov/system/stellar_items/image_files/38_saturn_1600x900.jpg"
        )
    )

    @JvmStatic val GENERAL_TOPICS = listOf(
        Topic(
            id = 1,
            name = "Stars",
            imageUrl = "https://wallpapersite.com/images/pages/pic_w/6362.jpg"
        ),
        Topic(
            id = 2,
            name = "Galaxies",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/M82_HST_ACS_2006-14-a-large_web.jpg/1280px-M82_HST_ACS_2006-14-a-large_web.jpg"
        ),
        Topic(
            id = 3,
            name = "NASA",
            imageUrl = "http://ste.india.com/sites/default/files/2014/08/28/267516-nasa.jpg"
        ),
        Topic(
            id = 4,
            name = "SpaceX",
            imageUrl = "https://techcrunch.com/wp-content/uploads/2018/02/spacex-falcon-heavy-181.jpg?w=730&crop=1"
        ),
        Topic(
            id = 5,
            name = "Space",
            imageUrl = "https://spacenews.com/wp-content/uploads/2018/05/24359364107_152b0152ff_k-879x485.jpg"
        )
    )

    @JvmStatic val ATRICLES = listOf(
        Article(
            id = 101,
            title = "The Sun accounts for 99.86% of the mass in the solar system",
            text = "It has a mass of around 330,000 times that of Earth. It is three quarters hydrogen and most of its remaining mass is helium.",
            imageUrl = "https://cdn.britannica.com/700x450/25/160325-004-AD594C66.jpg"
        ),
        Article(
            id = 102,
            title = "Over one million Earth’s could fit inside the Sun",
            text = "If you were to fill a hollow Sun with spherical Earths, somewhere around 960,000 would fit inside. However, if you squashed those Earths to ensure there was no wasted space then you could fit 1,300,000 Earths inside the Sun. The surface area of the Sun is 11,990 times that of Earth.",
            imageUrl = ""
        ),
        Article(
            id = 103,
            title = "One day the Sun will consume the Earth",
            text = "The Sun will continue to burn for about 130 million years after it burns through all of its hydrogen, instead burning helium. During this time it will expand to such a size that it will engulf Mercury, Venus, and Earth. When it reaches this point, it will have become a red giant star.",
            imageUrl = ""
        ),
        Article(
            id = 104,
            title = "The energy created by the Sun’s core is nuclear fusion",
            text = "This huge amount of energy is produced when four hydrogen nuclei are combined into one helium nucleus.",
            imageUrl = ""
        ),
        Article(
            id = 105,
            title = "The Sun is almost a perfect sphere",
            text = "Considering the sheer size of the Sun, there is only a 10 km difference in its polar and equatorial diameters – this makes it the closest thing to a perfect sphere observed in nature.",
            imageUrl = ""
        ),
        Article(
            id = 106,
            title = "The Sun is travelling at 220 km per second",
            text = "It is around 24,000-26,000 light-years from the galactic centre and it takes the Sun approximately 225-250 million years to complete one orbit of the centre of the Milky Way.",
            imageUrl = ""
        ),
        Article(
            id = 107,
            title = "The Sun will eventually be about the size of Earth",
            text = "Once the Sun has completed its red giant phase, it will collapse. It’s huge mass will be retained, but it will have a volume similar to that of Earth. When that happens, it will be known as a white dwarf.",
            imageUrl = ""
        ),
        Article(
            id = 108,
            title = "It takes eight minutes for light reach Earth from the Sun",
            text = "The average distance from the Sun to the Earth is about 150 million km. Light travels at 300,000 km per second so dividing one by the other gives you 500 seconds – eight minutes and twenty seconds. This energy can reach Earth in mere minutes, but it takes millions of years to travel from the Sun’s core to its surface.",
            imageUrl = ""
        ),
        Article(
            id = 109,
            title = "The Sun is halfway through its life",
            text = "At 4.5 billion years old, the Sun has burned off around half of its hydrogen stores and has enough left to continue burning hydrogen for another 5 billion years. Currently the Sun is a yellow dwarf star.",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/Sunspots_and_Solar_Flares.jpg/220px-Sunspots_and_Solar_Flares.jpg"
        ),
        Article(
            id = 110,
            title = "The distance between Earth and Sun changes",
            text = "This is because the Earth travels on a elliptical orbit path around the Sun. The distance between the two ranges from 147 to 152 million km. This distance between them is one Astronomical Unit (AU).",
            imageUrl = ""
        )
    )

    @JvmStatic val FOOTER_INFO = FooterInfo(
        id = 1000,
        message = "Want to see more?",
        buttonTitle = "Sign up",
        imageUrl = "https://i1.sndcdn.com/artworks-000239492068-ux2g3f-t500x500.jpg"
    )


}